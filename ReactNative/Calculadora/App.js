import { StatusBar } from 'expo-status-bar';
import { useState } from 'react';
import { StyleSheet, Text, View, Button, TextInput } from 'react-native';

export default function App() {

  const [resultado, setResultado] = useState(0)
  const [numero1, setNumero1] = useState(0)
  const [numero2, setNumero2] = useState(0)

  const sumar = () => {
    setResultado(parseFloat(numero1) + parseFloat(numero2))
  }

  const restar = () => {
    setResultado(parseFloat(numero1) - parseFloat(numero2))
  }

  const multiplicar = () => {
    setResultado(parseFloat(numero1) * parseFloat(numero2))
  }

  return (
    <View style={styles.container}>
      <Text>Calculadora {"\n"}</Text>
      <StatusBar style="auto" />
      <Text> Ingrese el primer numero:  {"\n"}</Text>
      <TextInput
        style={styles.cajaTexto}
        value={numero1}
        onChangeText={(numero) => {
          setNumero1(numero)
        }}
      />
      <Text> Ingrese el segundo numero:  {"\n"}</Text>
      <TextInput
        style={styles.cajaTexto}
        value={numero2}
        onChangeText={(numero) => {
          setNumero2(numero)
        }}
      />
      <Button
        title='Sumar'
        onPress={
          sumar
        }
      />
      <Button
        title='Restar'
        onPress={
          restar
        }
      />
      <Button
        title='Multiplicar'
        onPress={
          multiplicar
        }
      />

      <Text> {"\n"}El resultado es: {resultado}</Text>
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
  cajaTexto: {
    borderColor: "Black",
    borderWidth: 1,
    padding: 5
  }
});
