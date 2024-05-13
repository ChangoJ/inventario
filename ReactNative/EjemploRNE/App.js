import { StatusBar } from 'expo-status-bar';
import React, { useState } from 'react';
import { Alert, StyleSheet, Text, View } from 'react-native';
import { Button, Icon, Input } from '@rneui/base';

export default function App() {

  let [name, setName] = useState()

  return (
    <View style={styles.container}>
      <Text>RNE</Text>
      <Input
        value={name}
        onChangeText = {setName}
        placeholder='Ingrese su nombre'
        label= "Nombre"
      />
      <Button
        title="OK"
        icon={{
          name: 'home',
          type: 'font-awesome',
          size: 15,
          color: 'white',
        }}
        onPress = {()=>{
          Alert.alert("Info", "Su nombre es: "+name)
        }}
      />

      <Button
        title="Cancel"
        icon={<Icon
          name='reddit'
          type='zodial'
          color='white'
        />}
      />

      <Icon
        name='albums'
        type='ionicon'
        color='black'
      />

    
  <Text>{name}</Text>
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
});
