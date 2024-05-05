import { StatusBar } from 'expo-status-bar';
import { Button, StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <View style={styles.container2}></View>
      <View style={styles.container3}>
        <View style={styles.container4}></View>
        <View style={styles.container5}>
          <View style={styles.container6}></View>
          <View style={styles.container7}>
            <Button title='BOTON 1' />
            <Button title='BOTON 2' />
            <Button title='BOTON 3' />

          </View>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'gray',
    flexDirection: "column"
  },
  container2: {
    flex: 1,
    backgroundColor: 'blue',
    flexDirection: "column"
  },
  container3: {
    flex: 3,
    backgroundColor: 'green',
    flexDirection: "column"
  },
  container4: {
    flex: 1,
    backgroundColor: 'yellow',
    flexDirection: "column"
  },
  container5: {
    flex: 1,
    backgroundColor: 'orange',
    flexDirection: "row"
  },
  container6: {
    flex: 1,
    backgroundColor: 'gray',
    flexDirection: "column"
  },
  container7: {
    flex: 2,
    backgroundColor: 'purple',
    flexDirection: "column",
    justifyContent: "space-around",
    alignItems: "stretch"
  },

});
