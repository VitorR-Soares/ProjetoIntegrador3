const usuarioLogado = sessionStorage.getItem("usuarioLogado");

if (!usuarioLogado) {
  window.location.href = "login.html"; // redireciona se não estiver logado
}

// COLETA DE ELEMENTOS DO DOM
const form = document.getElementById("product_form");
const input_nome = document.getElementById("input_nome");
const input_horas = document.getElementById("input_horas");
const input_complexidade = document.getElementById("input_complexidade");
const input_categ = document.getElementById("input_categ");
const exibe_preco = document.getElementById("exibe_preco");
const btn_calcula_preco = document.getElementById("btn_calcula_preco");

// ROTINA DE PREENCHER SELECT DE CATEGORIA

const preencheCategorias = () => {    
    fetch('http://localhost:8080/categoria')
            .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao buscar produtos");
            }
            return response.json();
            })
            .then(categorias => {
                categorias.forEach(categoria => {
                    console.log(categoria.nome_categoria);
                    const option = document.createElement('option');
                    option.value = categoria.id; // ou outro nome de campo de ID da categoria
                    option.textContent = categoria.nome_categoria; // ou outro campo como 'descricao'
                    input_categ.appendChild(option);
                });
            })
            .catch(error => {
                console.error("Erro na busca:", error);
                alert("Ocorreu um erro na busca. Verifique se o servidor está rodando.");
            });   
};


// ROTINA DE CÁLCULO DE PREÇO

const calculaPreco = (horas, nivel_complexidade) => {
    
    let fator_complexidade;
    
    if(nivel_complexidade === 1){
        fator_complexidade = 1.2;
    } else if (nivel_complexidade === 2){
        fator_complexidade = 1.4;        
    } else if(nivel_complexidade === 3){
        fator_complexidade = 1.6;
    }
    
    return (10*horas*fator_complexidade);
    
};

btn_calcula_preco.addEventListener("click", (evt) => {
    evt.preventDefault();
    
    const horas = parseInt(input_horas.value);
    const complexidade = parseInt(input_complexidade.value);
    
    console.log(horas);
    console.log(complexidade);
    
    try {
        const preco_final = calculaPreco(horas, complexidade);
        console.log(preco_final);
        exibe_preco.innerHTML = 'R$' + preco_final;
        
    } catch (error){
        alert("Erro ao caalcular o preço");
    }
    
});

// ROTINA DE CADASTRO DE PRODUTO

form.addEventListener("submit", (event) => {
  event.preventDefault();

  const nome_produto = input_nome.value.trim();
  const valor_unit = calculaPreco(parseInt(input_horas.value), parseInt(input_complexidade.value));
  const qtd = 10;
  const horas_impressao = input_horas.value;
  const idCategoria = input_categ.value;

  if (!nome_produto || !valor_unit || !qtd || !horas_impressao || !idCategoria) {
    alert("Preencha todos os campos antes de cadastrar.");
    return;
  }

  const novoProduto = {
    nome_produto: nome_produto,
    valor_unit: valor_unit,
    qtd: qtd,
    horas_impressao: horas_impressao,
    categoria: {
      id: idCategoria
    }
  };

  fetch('http://localhost:8080/produto', { 
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(novoProduto)
  })
    .then(response => {
      if (!response.ok) {
        throw new Error("Erro ao cadastrar produto");
      }
      return response.json();
    })
    .then(data => {
      alert("Produto cadastrado com sucesso!");
      window.location.href = "gestao_cadastros.html";
    })
    .catch(error => {
      console.error("Erro ao cadastrar produto:", error);
      alert("Erro ao cadastrar produto. Verifique os dados ou o servidor.");
    });
});

// CHAMANDO MÉTODOS AO CARREGAR A PÁGINA
preencheCategorias();
console.log(calculaPreco(7,1));