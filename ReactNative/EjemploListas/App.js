import { StatusBar } from 'expo-status-bar';
import React, { useState } from 'react';
import { StyleSheet, Text, View, FlatList, TextInput, Button, Alert } from 'react-native';


let personas = [
  {
    nombre: "Thor",
    apellido: "Thillas",
    cedula: "0245789664"
  }, {
    nombre: "Amber",
    apellido: "Flores",
    cedula: "0145789777"
  },
  {
    nombre: "Peter",
    apellido: "Parker",
    cedula: "1745789664"
  }
]

//determinar indica si esta creando una nueva persona o modificando
let esNuevo = true;
//esta variable almacena el indice del arreglo del elemento seleccioando para editar
let inidiceSeleccionado = 1


export default function App() {
  const [cedula, setCedula] = useState()
  const [nombre, setNombre] = useState()
  const [apellido, setApellido] = useState()
  const [numElementos, setNumElementos] = useState(personas.length)


  let ItemPersona = ({indice, persona}) => {
    return (
      <View style={styles.itemPersona}>
        <View style={styles.itemIndice}>
          <Text>{indice}</Text>
        </View>
        <View style={styles.itemContenido}>
          <Text style={styles.textoPrincipal}>{persona.nombre} {persona.apellido} </Text>
          <Text style={styles.textoSecundario}>{persona.cedula}</Text>
        </View>
        <View style={styles.itemBotones}>
          <Button
            title=' E '
            color="green"
            onPress={() => {
              console.log("datos: ", persona)
              setCedula(persona.cedula)
              setNombre(persona.nombre)
              setApellido(persona.apellido)
              esNuevo = false
              inidiceSeleccionado = indice

            }}

          />
          <Button
            title=' X '
            color="red"
            onPress={() => {
              inidiceSeleccionado = indice
              personas.splice(inidiceSeleccionado, 1)
              setNumElementos(personas.length)
            }}

          />
        </View>
      </View>
    )
  }


  let limpiar = () => {
    setCedula(null)
    setNombre(null)
    setApellido(null)
    esNuevo = true
  }

  let existePersona =()=>{
    for (let i = 0; i < personas.length; i++) {
      if(personas[i].cedula == cedula){
        return true
      } 
    }

    return false
  }

  let guardarPersona = () => {

    if (esNuevo ) {
      if(existePersona()){
        Alert.alert("INFO","Ya existe una personas con la cédula "+cedula)
      }else{
        let persona = {
          nombre: nombre,
          apellido: apellido,
          cedula: cedula
        }
  
        personas.push(persona)
      }
    } else {
      personas[inidiceSeleccionado].nombre = nombre
      personas[inidiceSeleccionado].apellido = apellido
    }
    limpiar()
    setNumElementos(personas.length)
  }

  return (
    <View style={styles.container}>
      <View style={styles.areaCabecera}>
        <Text>PERSONAS</Text>
        <TextInput
          style={styles.txt}
          value={cedula}
          placeholder='Ingrese su cedula'
          onChangeText={
            setCedula
          }
          keyboardType='numeric'
          editable={esNuevo}
        />
        <TextInput
          style={styles.txt}
          value={nombre}
          placeholder='Ingrese su nombre'
          onChangeText={
            setNombre
          }
        />
        <TextInput
          style={styles.txt}
          value={apellido}
          placeholder='Ingrese su apellido'
          onChangeText={
            setApellido
          }
        />
        <View style={styles.areaBotones}>
          <Button
            title='Guardar'
            onPress={() => {
              guardarPersona()
            }

            }

          />
          <Button
            title='Nuevo'
            onPress={limpiar}
          />
        </View>
        <Text>Elementos: {numElementos}</Text>
      </View>
      <View style={styles.areaContenido}>
        <FlatList
          style={styles.lista}
          data={personas}
          renderItem={({index, item}) => {
            return <ItemPersona
              indice={index}
              persona={item}

            />
          }}

          keyExtractor={(item) => {
            return item.cedula;
          }}
        />
      </View>
      <View style={styles.areaPie}>
        <Text>Autor: Jordan Chango</Text>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    // backgroundColor: 'lightblue',
    flexDirection: "column",
    justifyContent: "flex-start",
    alignItems: "stretch",
    paddingTop: 50,
    paddingHorizontal: 10
  },
  lista: {
    //backgroundColor: 'lightpink'
  },
  itemPersona: {
    backgroundColor: "lemonchiffon",
    marginBottom: 10,
    padding: 10,
    flexDirection: "row"
  },
  itemIndice: {
    flex: 1,
    //backgroundColor: "darkgrey",
    justifyContent: "center",
    alignItems: "center"
  },
  itemContenido: {
    flex: 9,
    //backgroundColor: "darkorange",
    paddingLeft: 6

  },
  itemBotones: {
    flexDirection: "row",
    justifyContent: "space-evenly",
    alignItems: "center",
    flex: 2,
    backgroundColor: "darkorange"

  },


  textoPrincipal: {
    fontSize: 16
  },
  textoSecundario: {
    fontStyle: "italic",
    fontSize: 16
  },
  areaCabecera: {
    flex: 4,
    //backgroundColor: "red",
    justifyContent: "center"
  },
  areaContenido: {
    flex: 6,
    //backgroundColor: "green"
  },
  areaPie: {
    flex: 1,
    //backgroundColor: "lightblue",
    justifyContent: "center",
    alignItems: "flex-end"
  },
  txt: {
    borderWidth: 1,
    borderColor: "gray",
    padding: 5,
    borderRadius: 5,
    marginBottom: 5
  },
  areaBotones: {
    flexDirection: "row",
    justifyContent: "space-evenly",
  }
});
