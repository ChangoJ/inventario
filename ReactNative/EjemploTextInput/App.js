import { StatusBar } from 'expo-status-bar';
import { useState } from 'react';
import { Button, StyleSheet, Text, TextInput, View } from 'react-native';

export default function App() {

  const [nombre, setNombre] = useState("Ingrese su nombre")
  const [apellido, setApellido] = useState("Ingrese su apellido")
  const [nombreCompleto, setNombreCompleto] = useState("")

  return (
    <View style={styles.container}>
      <Text>Ejemplo TextInput{"\n"}</Text>
      <Text>Hola, {nombreCompleto}{"\n"}</Text>
      <StatusBar style="auto" />
      <TextInput
        style={styles.cajaTexto}
        value={nombre}
        onChangeText={(texto) => {
          setNombre(texto)
        }}
      />

      <TextInput
        style={styles.cajaTexto}
        value={apellido}
        onChangeText={(texto) => {
          setApellido(texto)
        }}
      />
      <Button
        title='Saludar'
        onPress={() => {
          let completo = nombre+ " "+apellido
          setNombreCompleto(completo)
        }}
      />
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
    borderColor: "black",
    borderWidth: 1,
    padding: 7
  }
});
