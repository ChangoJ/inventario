import { View,Text,Button,StyleSheet } from "react-native";

export const Products = ({navigation}) =>{
    return <View style={styles.container}>
            <Text>PRODUCTS</Text>
            <Button
            title="IR A HOME"
            onPress={()=>{
                navigation.navigate("HomeNav")
            }}
            />
        </View>
}

const styles = StyleSheet.create({
    container:{
        flex: 1,
        backgroundColor: "#fff"
    }
})