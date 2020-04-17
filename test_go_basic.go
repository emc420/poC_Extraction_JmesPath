

package main

import "fmt"
import "go-jmespath"
import "encoding/json"


func main() {
	var result float64
	var res2, res3 []interface {}
	
	//Packet 1 
	result = basic1()
	fmt.Printf("The value id %f\n", result)
	
	//Packet 2
	res2 = basic2()
	
	for i :=0 ; i<len(res2); i++{
		fmt.Printf("The value basic2 %f\n", res2[i])
	}
	//Packet 3
	res3 = basic3()
	
	for j :=0 ; j<len(res3); j++{
		fmt.Printf("The value basic3 %d\n", res3[j])
	}
}

func basic1() float64 {
   /* local variable declaration */
   var moBanda float64
   var jsondata = []byte(`{"temperature": 22.6,"humidity": 41,"light": 39}`) // your data
   var data interface{}
   err := json.Unmarshal(jsondata, &data)
   result, err := jmespath.Search("humidity", data)
   moBanda = result.(float64)
   fmt.Sprint(err)
   return moBanda
}
func basic2() []interface {} {
   /* local variable declaration */
   var jsondata = []byte(`{
	"firmwareType": "SenlabT",
    "id": "datalog_transmission",
    "measures": [
        {
            "id": "temperature",
            "timestamp": 1580980445688,
            "value": 11.625,
            "time": "2020-02-06T09:14:05.688Z"
        },
        {
            "id": "temperature",
            "timestamp": 1580980545688,
            "value": 11.625,
            "time": "2020-02-06T09:15:45.688Z"
        },
        {
            "id": "temperature",
            "timestamp": 1580980645688,
            "value": 11.6875,
            "time": "2020-02-06T09:17:25.688Z"
        },
        {
            "id": "battery_current_level",
            "timestamp": 1580980695688,
            "value": 30,
            "time": "2020-02-06T09:18:15.688Z"
        }
    ],
    "parameters": [],
    "events": []  
   
   }`) // your data
   var data interface{}
   err := json.Unmarshal(jsondata, &data)
   result, err := jmespath.Search("measures[?id == 'temperature'].value", data)
   //fmt.Println(reflect.TypeOf(result))
   //moBanda = result.([]float64)
   fmt.Sprint(err)
   return result.([]interface{})
}

func basic3() []interface {} {
   /* local variable declaration */
   var jsondata = []byte(`{
	"EndPoint": 0,
    "Report": "Standard",
    "CommandID": "ReportAttributes",
    "ClusterID": "TIC_CBE",
    "AttributeID": "Attribute_0",
    "AttributeType": "ByteString",
    "Data": {
        "TICFieldList": {
            "DescHeader": {
                "Obsolete": "No",
                "Report": "Standard",
                "PresentField": "DescVarBitfield",
                "SizeOf": 0
            },
            "BitField": [0, 0, 0, 0, 1, 248, 0],
            "BBRHCJB": 123456789,
            "BBRHPJB": 999999999,
            "BBRHCJW": 999999999,
            "BBRHPJW": 999999999,
            "BBRHCJR": 999999999,
            "BBRHPJR": 999999999
        }
    },
    "Cause": []
   }`) // your data
   var data interface{}
   err := json.Unmarshal(jsondata, &data)
   result, err := jmespath.Search("to_array(@) | [?CommandID == 'ReportAttributes' && AttributeID == 'Attribute_0'].Data.TICFieldList.BBRHCJB", data)
   //fmt.Println(reflect.TypeOf(result))
   //moBanda = result.([]float64)
   fmt.Sprint(err)
   return result.([]interface{})
}