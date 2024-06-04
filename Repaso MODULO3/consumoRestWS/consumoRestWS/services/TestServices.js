export const getAllPostsService = () => {
    fetch('https://jsonplaceholder.typicode.com/posts')
      .then((response) => {return response.json()})
      .then((json) => {console.log(json)})
  }

  export const createPostService = (post, fnExito) => {
    const config={
        method: 'POST',
        body: JSON.stringify({
            title: post.title,
            body: post.body,
            userId: 1
        }),
        headers: {
            'Content-type': 'application/json',
        }
    };

    fetch('https://jsonplaceholder.typicode.com/posts',config)
    .then((response) =>{return response.json()})
    .then((json) => {console.log(json); fnExito()})

  }
  
  export const updatePostService = () => {
    const config={
        method: 'PUT',
        body: JSON.stringify({
            id: 1,
            title: 'Mensaje final',
            body: 'Hasta la vista baby',
            userId: 1
        }),
        headers: {
            'Content-type': 'application/json',
        }
    };

    fetch('https://jsonplaceholder.typicode.com/posts/1',config)
    .then((response) =>{return response.json()})
    .then((json) => {console.log(json)})

  }

  export const getByUserIdService = () => {
    
    fetch('https://jsonplaceholder.typicode.com/posts?userId=1')
    .then((response) =>{return response.json()})
    .then((json) => {console.log(json)})

  }
  
  
  export const getAllProductsService = () => {
    fetch('https://fakestoreapi.com/products')
        .then((response) => response.json())
        .then((json) => { console.log(json) });
}

export const createProductService = () => {
    const config = {
        method: 'POST',
        body: JSON.stringify({
            title: 'Nuevo producto',
            price: 29.99,
            description: 'Descripción del nuevo producto',
            image: 'https://i.pravatar.cc',
            category: 'electrónica'
        }),
        headers: {
            'Content-type': 'application/json',
        }
    };

    fetch('https://fakestoreapi.com/products', config)
        .then((response) => response.json())
        .then((json) => { console.log(json) });
}

export const updateProductService = () => {
    const config = {
        method: 'PUT',
        body: JSON.stringify({
            title: 'Producto actualizado',
            price: 39.99,
            description: 'Descripción actualizada del producto',
            image: 'https://i.pravatar.cc',
            category: 'electrónica'
        }),
        headers: {
            'Content-type': 'application/json',
        }
    };

    fetch(`https://fakestoreapi.com/products/1`, config)
        .then((response) => response.json())
        .then((json) => { console.log(json) });
}


export const getDocumentTypes = () => {
    
    fetch('http://192.168.100.15:8080/inventarios/rest/tiposdocumento/recuperar')
    .then((response) =>{return response.json()})
    .then((json) => {console.log(json)})

  }




export const createDocumentTypeService = (post, fnExito) => {
    const config={
        method: 'POST',
        body: JSON.stringify({
            codigo: post.codigo,
            descripcion: post.descripcion
        }),
        headers: {
            'Content-type': 'application/json',
        }
    };

    fetch('http://192.168.100.15:8080/inventarios/rest/tiposdocumento/crear',config)
    .then((response) =>{return response.json()})
    .then((json) => {console.log(json); fnExito()})

}