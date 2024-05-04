import { StatusBar } from 'expo-status-bar';
import { useState } from 'react';
import { Alert, Button, StyleSheet, Text, View } from 'react-native';

export default function App() {
  /*const arreglo = useState(0);
  const contadorEstado = arreglo[0]
  const setContadorEstado = arreglo[1]*/

  const [contadorEstado, setContadorEstado] = useState(0)
  const [vidas, setContadorVidas] = useState(5)

  const incrementar = () => {
    setContadorEstado(contadorEstado + 1)
  }

  const decrementar = () => {
    setContadorEstado(contadorEstado - 1)
  }

  const decrementarVidas = () => {

    if (vidas == 0) {
      Alert.alert("ADVERTENCIA", "GAMER OVER")
    } else {
      setContadorVidas(vidas - 1)
    }

  }

  const incrementarVidas = () => {
    setContadorVidas(vidas + 3)
  }

  return (
    <View style={styles.container}>
      <Text>Variables de Estado</Text>
      <Text>Contador Estado: {contadorEstado}</Text>
      <Text>VIDAS: {vidas}</Text>
      <StatusBar style="auto" />
      <Button
        title="INCREMENTAR"
        onPress={incrementar}
      />

      <Button
        title="DECREMENTAR"
        onPress={decrementar}
      />
      <Button
        title='PERDER VIDA'
        onPress={decrementarVidas}
      />
      <Button
        title='PREMIAR'
        onPress={incrementarVidas} />
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
});
