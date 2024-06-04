import { View, StyleSheet } from 'react-native'
import { Button, Text } from '@rneui/base'
import { getAllPostsService } from '../services/TestServices'
import { createPostService } from '../services/TestServices'
import { updatePostService } from '../services/TestServices'
import { getByUserIdService } from '../services/TestServices'


import { createProductService } from '../services/TestServices'
import { updateProductService } from '../services/TestServices'
import { getAllProductsService } from '../services/TestServices'

import { getDocumentTypes } from '../services/TestServices'



export const TestWebServices = () => {



  return <View style={styles.container}>
    <Text style={styles.textContainer}>MODULO 3</Text>
    <View style={styles.buttonContainer}>
      <Button
        title="Recuperar Posts JORDAN CHANGO"
        onPress={
          getAllPosts
        }
      />
      <Button
        title="Crear Post"
        onPress={
          createPost
        }
      />
      <Button
        title="Actualizar Post"
        onPress={
          updatePost
        }
      />
      <Button
        title="Filtrar"
        onPress={
          getByUserId
        }
      />
      <Button
        title="XXX-Obtener Productos"
        onPress={getAllProducts}
      />

      <Button
        title="YYY-Crear Producto"
        onPress={createProduct}
        
      />

      <Button
        title="ZZZ- Actualizar Producto"
        onPress={updateProduct}
      />

<Button
        title="MI IP-TIPO DE DOCUMENTOS"
        onPress={getDocument}
      />

    </View>
  </View>
}

const getAllPosts = () =>{
    getAllPostsService();
}

const createPost = () =>{
  createPostService();
}

const updatePost = () =>{
  updatePostService();
}

const getByUserId = () =>{
  getByUserIdService();
}

///

const getAllProducts = () => {
  getAllProductsService();
}

const createProduct = () => {
  createProductService();
}

const updateProduct = () => {
  updateProductService();  
}


const getDocument = () => {
  getDocumentTypes();  
}


const styles = StyleSheet.create({
  container: {
    flex: 1,
    flexDirection: 'column',
    backgroundColor: '#fff',
  },
  textContainer: {
    flex: 1,
    textAlign: 'center',
    fontSize: 18,
    marginVertical: 10
  },
  buttonContainer: {
    flex: 6,
    alignItems: 'stretch',
    justifyContent: 'space-around',
    marginHorizontal: 10

  }
});