import { StatusBar } from 'expo-status-bar';
import { useState } from 'react';
import { StyleSheet, Text, View, TextInput, Button } from 'react-native';

export default function App() {

  const [valor, setValor] = useState(0)
  const [resultado, setResultado] = useState(0)

  const convertirAPesosMexicanos = () => {
    setResultado(parseFloat(valor * 21) + " MXN")
  }

  const convertirAPesosColombianos = () => {
    setResultado(parseFloat(valor * 3800)  + " COP")
  }

  const convertirAEuros = () => {
    setResultado(parseFloat(valor * 0.82)  + " EUR")
  }


  return (
    <View style={styles.container}>
      <Text>Convertidor {"\n"}</Text>
      <Text>Ingrese el valor en Dolares $: {"\n"}</Text>
      <TextInput
      style = {styles.cajaInput}
        value={String(valor)}
        onChangeText={(valor) => {
          setValor(valor)
        }}
      />

      <Button
        title='PESOS MEXICANOS'
        onPress={
          convertirAPesosMexicanos
        }
      />

      <Button
        title='PESOS COLOMBIANOS'
        onPress={
          convertirAPesosColombianos
        }
      />

      <Button
        title='PESOS EUROS'
        onPress={
          convertirAEuros
        }
      />
      <Text>El resultado es: {resultado}</Text>

      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  cajaInput: {
    borderColor: "black",
    borderWidth: 1,
    padding: 5
  }
});
