import { View, Text, StyleSheet, Button } from "react-native"


export const Contacts = ({navigation}) => {
    return <View style={styles.container}>
        <Text>ESTOY EN CONTACTS</Text>
        <Button
            title="IR A HOME"
            onPress={()=>{
                navigation.navigate('HomeNav')
            }}
        />
    </View>
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: "#fff",
        alignItems: "center",
        justifyContent: "center"
    }
}
)