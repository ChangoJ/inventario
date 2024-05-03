
ejecutarOperacion = (operar) => {
    let resultado
    let numero1 = recuperarEntero("txtvalor1")
    let numero2 = recuperarEntero("txtvalor2")

    resultado = operar(numero1, numero2)
    console.log("El resultado es " + resultado)

}

sumar = (numero1, numero2) => {
    let resultado
    resultado = numero1 + numero2;
    return resultado
}

restar = (numero1, numero2) => {
    let resultado
    resultado = numero1 - numero2
    return resultado
}

ejecutar = (fn) => {
    console.log("Estoy ejecutando funciones")
    fn()
}

imprimir = () => {
    console.log("estoy imprimiendo")
}

saludar = () => {
    alert("Aprendiendo programacion funcional")
}

testEjecutar = () => {
    ejecutar(imprimir)
    ejecutar(saludar)
    ejecutar(() => {
        alert("Soy una funcion sin nombre")
    });
}