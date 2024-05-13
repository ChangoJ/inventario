import { View, Text, StyleSheet, Alert } from "react-native"


export const ContenidoA = () =>{
    return (
        <View style={styles.container}>
            <Text>CONTENIDO A</Text>
        </View>
    )
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    saveButton: {
        backgroundColor: "green"
    }
});