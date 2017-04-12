/**
 *  King Of Fans Zigbee Fan Controller - Fan Speed Child Device
 *
 *  Copyright 2017 Stephan Hackett
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "KOF Zigbee Fan Controller - Fan Speed Child Device", namespace: "stephack", author: "Stephan Hackett") {
		capability "Actuator"
        capability "Switch"
        capability "Light"
        capability "Sensor" 
        capability "Momentary"
        
   }     


	tiles(scale: 2) {    	
	
		standardTile("switch", "switch", width: 2, height: 2) {
     		state "default", label:"Push", action: "on", icon:"https://raw.githubusercontent.com/dcoffing/SmartThingsPublic/master/devicetypes/dcoffing/hampton-bay-universal-ceiling-fan-light-controller.src/Fan175xfinal.png", backgroundColor: "#ffffff", nextState: "turningOn"
			state "on", label: "on", action: "off", icon:"https://raw.githubusercontent.com/dcoffing/SmartThingsPublic/master/devicetypes/dcoffing/hampton-bay-universal-ceiling-fan-light-controller.src/Fan175xfinal.png", backgroundColor: "#336600", nextState: "turningOff"
        	state "turningOn", label:"ADJUST", action: "", icon:"https://raw.githubusercontent.com/dcoffing/SmartThingsPublic/master/devicetypes/dcoffing/hampton-bay-universal-ceiling-fan-light-controller.src/Fan175xfinal.png", backgroundColor: "#2179b8"        	 
		}     	
    
    	main(["switch"])        
		details(["switch"])    
    
	}
}


def on() {
	push()        
}

def off() {
	push()         
}

def push() {
log.info "CHILD PUSH RECEIVED"
	sendEvent(name: "switch", value: "on", isStateChange: true, displayed: false)
	sendEvent(name: "switch", value: "off", isStateChange: true, displayed: false)
	sendEvent(name: "momentary", value: "pushed", isStateChange: true)
    
	def myChildId = device.deviceNetworkId
	def myParentId = parent.deviceNetworkId

	switch(myChildId) {
		case "${myParentId}-1":
    		parent.fanOne()
    	break
    	case "${myParentId}-2":
    		parent.fanTwo()
    	break
    	case "${myParentId}-3":
    		parent.fanThree()
   		break
    	case "${myParentId}-4":
    		parent.fanFour()
    	break
    	case "${myParentId}-5":
    		parent.fanAuto()
      	break               
	}        
}