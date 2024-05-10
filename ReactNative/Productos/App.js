import { StatusBar } from 'expo-status-bar';
import React, { useState } from 'react';
import { StyleSheet, Text, View, FlatList, ScrollView, TextInput, Alert, Button } from 'react-native';


let productos = [
  {
    nombre: "Manzana",
    categoria: "Fruta",
    precioCompra: 0.22,
    precioVenta: 0.30,
    id: 100
  }, {
    nombre: "Zanahoria",
    categoria: "Vegetal",
    precioCompra: 0.12,
    precioVenta: 0.15,
    id: 101
  }
]

let esNuevo = true
let productoSeleccionado = -1

export default function App() {

  const [codigo, setCodigo] = useState()
  const [nombre, setNombre] = useState()
  const [categoria, setCategoria] = useState()
  const [precioCompra, setPrecioCompra] = useState()
  const [precioVenta, setPrecioVenta] = useState(0)
  const [numElementosProductos, setnumElementosProductos] = useState(productos.length)


  let ItemProducto = (props) => {
    return (
      <View style={styles.itemProductos} >
        <View style={styles.itemId}>
          <Text>{props.producto.id}</Text>
        </View>
        <View style={styles.itemContenido}>
          <Text>{props.producto.nombre}</Text>
          <Text style={styles.textoCategoria}>{props.producto.categoria}</Text>
        </View>
        <View style={styles.itemPrecio}>
          <Text style={styles.textoPrecio}>{props.producto.precioVenta}</Text>
        </View>
        <View style={styles.itemBotones}>
          <Button
            title=' E '
            color="green"
            onPress={() => {
              console.log("datos: ", props.producto)
              setCodigo(props.producto.id.toString())
              setNombre(props.producto.nombre)
              setCategoria(props.producto.categoria)
              setPrecioCompra(props.producto.precioCompra.toString())
              setPrecioVenta(props.producto.precioVenta.toString())
              esNuevo = false
              productoSeleccionado = props.indice


            }}

          />
          <Button
            title=' X '
            color="red"
            onPress={() => {
              productoSeleccionado = props.indice
              productos.splice(productoSeleccionado, 1)
              setnumElementosProductos(productos.length)
            }}
          />
        </View>
      </View>
    )
  }

  let limpiar = () => {
    setCodigo(null)
    setNombre(null)
    setCategoria(null)
    setPrecioCompra(null)
    setPrecioVenta(null)
    
    esNuevo = true
  }

  let existeProducto = () => {
    for (let i = 0; i < productos.length; i++) {

      if (productos[i].id == codigo) {
        return true
      }

    }

    return false
  }

  let esVacio = () => {
    if (codigo == null ||
      nombre == null ||
      categoria == null ||
      precioCompra == null ||
      precioVenta == null) {
      return true
    }
    return false
  }

  let guardarProductos = () => {
    if (esNuevo) {
      if (existeProducto()) {
        Alert.alert("INFO", "Ya existe un producto con el código " + codigo)
      } else if (esVacio()) {
        Alert.alert("INFO", "Debe llenar todos los campos")
      } else {
        let producto = {
          nombre: nombre,
          categoria: categoria,
          precioCompra: parseFloat(precioCompra),
          precioVenta: parseFloat(precioVenta),
          id: parseInt(codigo)
        }
        productos.push(producto)
        limpiar()
      }

    } else {

      productos[productoSeleccionado].id = parseInt(codigo)
      productos[productoSeleccionado].nombre = nombre
      productos[productoSeleccionado].categoria = categoria
      productos[productoSeleccionado].precioCompra = parseFloat(precioCompra)
      productos[productoSeleccionado].precioVenta = parseFloat(precioVenta)
      limpiar()
    }

    setnumElementosProductos(productos.length)
  }

  return (
    <View style={styles.container}>

      <View style={styles.tituloContainer}>
        <Text style={styles.titulo}>PRODUCTOS</Text>
      </View>
      <View>
        <ScrollView>
          <View>
            <TextInput
              style={styles.cajaTexto}
              value={codigo}
              placeholder='CÓDIGO'
              onChangeText={setCodigo}
              keyboardType='numeric'
              editable={esNuevo}
            />
            <TextInput
              style={styles.cajaTexto}
              value={nombre}
              placeholder='NOMBRE'
              onChangeText={setNombre}
            />
            <TextInput
              style={styles.cajaTexto}
              value={categoria}
              placeholder='CATEGORIA'
              onChangeText={setCategoria}
            />
            <TextInput
              style={styles.cajaTexto}
              value={precioCompra}
              placeholder='PRECIO DE COMPRA'
              onChangeText={(texto) => {
                setPrecioCompra(texto)
                setPrecioVenta((parseFloat(texto) * 1.20).toFixed(2));

              }
              }
              keyboardType='numeric'
            />
            <TextInput
              style={styles.cajaTexto}
              value={precioVenta}
              placeholder='PRECIO DE VENTA'
              keyboardType='numeric'
              editable={false}
            />
          </View>
          <View style={styles.areaBotones}>
            <Button
              title='NUEVO'
              onPress={limpiar}
            />
            <Button
              title='GUARDAR'

              onPress={guardarProductos}
            />
            <Text>Productos: {numElementosProductos}</Text>
          </View>
        </ScrollView>
      </View>
      <View>
        <FlatList
          style={styles.lista}
          data={productos}
          renderItem={(elemento) => {
            return (
              <ItemProducto
                indice={elemento.index}
                producto={elemento.item}
              />
            )
          }}
          keyExtractor={(item) => {
            return item.id;
          }}
        />
      </View>

      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    flexDirection: 'column',
    justifyContent: 'flex-start',
    paddingVertical: 50,
    paddingHorizontal: 10
  },
  tituloContainer: {
    alignItems: "center",
  },
  titulo: {
    fontSize: 16,
    fontWeight: "bold",
    marginBottom: 20
  },
  lista: {

  },
  itemProductos: {
    backgroundColor: "lightgreen",
    marginBottom: 10,
    borderWidth: 1,
    flexDirection: "row",
    alignItems: "stretch",
    borderRadius: 5,
    padding: 5,
  },
  itemId: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center"
  },
  itemContenido: {
    flex: 4,
    justifyContent: "center",
    alignItems: "flex-start"
  },
  itemPrecio: {
    flex: 1,
    justifyContent: "center",
    alignItems: "flex-end"
  },
  itemBotones: {
    flex: 2,
    flexDirection: "row",
    justifyContent: "space-evenly",
  },
  textoPrecio: {
    fontWeight: "bold"
  },
  textoCategoria: {
    fontStyle: "italic"
  },
  cajaTexto: {
    borderWidth: 1,
    borderColor: "gray",
    padding: 5,
    marginBottom: 5,
    borderRadius: 5
  },
  areaBotones: {
    flexDirection: "row",
    justifyContent: "space-evenly",
    alignItems: "center",
    marginBottom: 20,
    marginTop: 5
  }

});
