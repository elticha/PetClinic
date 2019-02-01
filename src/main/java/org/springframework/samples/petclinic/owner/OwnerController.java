/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerRepository owners;


    public OwnerController(OwnerRepository clinicService) {
        this.owners = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/owners/new")
    public String initCreationForm(Map<String, Object> model) {
        Owner owner = new Owner();
        model.put("owner", owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            this.owners.save(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/owners/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Collection<Owner> results = this.owners.findByLastName(owner.getLastName());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.put("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
        Owner owner = this.owners.findById(ownerId);
        model.addAttribute(owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            this.owners.save(owner);
            return "redirect:/owners/{ownerId}";
        }
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        Map<String, Object> model = new HashMap<String, Object>();
        Owner owner = this.owners.findById(ownerId);
        
        String [] x= getCoordinatesAPI(owner).block().split("coordinates")[1].split("\\[")[1].split("\\]")[0].split(",");
        CenterData center = new CenterData();
        center.setCero(x[0]);
        center.setUno(x[1]);
        
        model.put("owner", owner);
        model.put("coordinates", center);
        return new ModelAndView("owners/ownerDetails", "model", model);
    }
    
    public Mono<String> getCoordinatesAPI(Owner owner) {
        WebClient webClient = WebClient.create("https://api.mapbox.com/geocoding/v5/mapbox.places");
        return webClient.get()
            .uri("/{address} {city} {estado} {country}.json?limit=1&access_token=pk.eyJ1IjoidG90b2ciLCJhIjoiY2pyOWVjMmsyMGExNzN5bW00bmV0Y28wNCJ9.j5ehwrvxguCDgCl-9Ah2LA",owner.getAddress(), owner.getCity(),owner.getEstado(), owner.getCountry())
            .retrieve()
            .bodyToMono(String.class);
    }
    
//    Owners report
    @GetMapping("/ownersReport")
    public String processFindForm2(Owner owner, BindingResult result, Map<String, Object> model) {
        Collection<Owner> results = this.owners.findByLastName("");
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "No owners to report", "Empty owners");
            return "owners/findOwners";
        } else {
            // multiple owners found
            model.put("selections", results);
            return "owners/ownersReport";
        }
    }

}
