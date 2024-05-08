import { StatusBar } from 'expo-status-bar';
import React, { useState } from 'react';
import { Button, StyleSheet, Text, TextInput, View } from 'react-native';

export default function App() {

  const [peso, setPeso] = useState(0);
  const [estatura, setEstatura] = useState(0);
  const [resultado, setResultado] = useState(0);
  const [mensaje, setMensaje] = useState("");

  const calcular = () => {
    let estaturaMetros = estatura /100
    let resultado = (parseFloat(peso) / (parseFloat(estaturaMetros) * parseFloat(estaturaMetros))).toFixed(2)

    setResultado(resultado)

    if (resultado < 18.5) {
      setMensaje("Su peso es inferior al normal")
    } else if (resultado >= 18.5 && resultado < 25) {
      setMensaje("Su peso es normal")
    } else if (resultado >= 25 && resultado < 30) {
      setMensaje("Su peso es superior al normal")
    } else if (resultado > 30) {
      setMensaje("Tiene obesidad")
    }

  }

  return (
    <View style={styles.container}>
      <Text>CALCULADORA IMC</Text>

      <Text style={styles.texto}>Peso: </Text>
      <TextInput
        style={styles.caja}
        value={peso}
        onChangeText={(texto) => {
          setPeso(texto)
        }}
        placeholder='Ingrese el peso en KG'
      />

      <Text style={styles.texto}>Estatura: </Text>
      <TextInput
        style={styles.caja}
        value={estatura}
        onChangeText={(texto) => {
          setEstatura(texto)
        }}
        placeholder='Ingrese la estatura en metros'
      />
      <StatusBar style="auto" />
      <Button
        title='Calcular'
        onPress={
          calcular
        }
      />

      <Text style={styles.texto}>El indice de masa corporal es: {resultado}</Text>
      <Text style={styles.texto}>{mensaje}</Text>

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    flexDirection: "column",
    justifyContent: 'center',
    alignItems: 'stretch',
    paddingHorizontal: 20
  },
  caja: {
    borderColor: "black",
    borderWidth: 1,
    padding: 5,
    marginVertical: 10
  },
  texto: {
    marginVertical: 10,
    fontSize: 14
  }

});
