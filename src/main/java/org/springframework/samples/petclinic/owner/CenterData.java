package org.springframework.samples.petclinic.owner;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author paula
 */
public class CenterData {
   public String cero;
   public String uno;
    
    CenterData(){
    }
    
    @JsonProperty("0")
    public String getCero(){
      return cero;
    }
    
    @JsonProperty("1")
    public String getUno(){
      return uno;
    }
    
    @JsonProperty("0")
    public void setCero(String cero){
      this.cero= cero;
    }
    
    @JsonProperty("1")
    public void setUno(String uno){
      this.uno= uno;
    }
    
    @Override
    public String toString(){
        return this.cero+", "+this.uno;
    }
}