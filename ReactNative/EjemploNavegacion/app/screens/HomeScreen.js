
import { View, Text, StyleSheet, Button } from "react-native"

export const Home = ({ navigation }) => {
    return <View style={styles.container}>
        <View style={styles.title}>
        <Text>HOME</Text>
        </View>
        
       <View style={styles.areaBotones}>
       <Button
        style={styles.boton}
            title="CONTACTOS"
            onPress={() => {
                navigation.navigate("ContactsNav")
            }
            }
        />
        <Button
         style={styles.boton}
            title="PRODUCTOS"
            onPress={() => {
                navigation.navigate("ProductsNav")
            }
            }
        />
       </View>
        
    </View>
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#fff",
        flexDirection: "column",
        justifyContent: "center"
    },
    title:{
        alignItems: "center",
        fontSize: 20
    },
    areaBotones:{
        flexDirection: "row",
        justifyContent: "space-evenly"

    }
})