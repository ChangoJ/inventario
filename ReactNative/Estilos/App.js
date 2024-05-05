import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <Button title='COMP 1'/>
      <Button title='COMP 2' color="green"/>
      <Button title='COMP 3'/>

      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: "colum",
    justifyContent: 'space-around',//principal
    alignItems: 'stretch',//secundario
  },
});
