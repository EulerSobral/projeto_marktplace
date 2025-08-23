import React, { useState } from "react"; 
import {Link} from "react-router-dom";
import './CadastrarProdutos.css'; 
import axios from "axios";

export default function CadastrarProdutos() {

  const [image, setImage] = useState(null); 
  const [nome, setNome] = useState("");
  const [preco, setPreco] = useState("");
  const [descricao, setDescricao] = useState("");
  const [categoria, setCategoria] = useState("Eletrônicos") 
  const [status, setStatus] = useState("Disponível")

  const handleChangeImage = (e) => {
    setImage(e.target.files[0]) 
  } 

  const handleChange = (e) => {
    const { name, value } = e.target;

    switch (name) {
      case "nome":
        setNome(value);
        break;
      case "categoria":
        setCategoria(value);
        break;
      case "status":
        setStatus(value);
        break;
      case "preco":
        setPreco(value);
        break;
      case "descricao":
        setDescricao(value);
        break;
      default:
        break;
    }
  };

  const handleUpload = async () => {
    const formData = new FormData()
    formData.append("imagem", image)
    formData.append("nome-produto", nome) 
    formData.append("preco-produto", preco) 
    formData.append("descricao", descricao)
    formData.append("categoria", categoria)
    formData.append("status", status)

    try {
     await axios.post("http://localhost:8081/cadastro-produtos", formData, {
        headers: { "Content-Type": "multipart/form-data" },
      }) 
      alert("Produto cadastrado com sucesso!")
    } catch (err) { 
      alert("Erro ao cadastrar produto. Tente novamente.")
      console.error(err)
    }
  };

  return ( 
    <> 
    <header className="header-cadastro-produto"> 
        <p className="p-link-header"><Link to="/produtos" className="link-header">Voltar</Link></p>
        <div className="div-header">
            <h1 className="h1-header">Cadastrar Produtos</h1>
            <p className="p-header">Adicione um novo produto ao sistema</p>
        </div>
    </header>
    <div className="div-container-cadastro-produto" >
        <form className="form-cadastro-produto" onSubmit={handleUpload} encType="multipart/form-data"> 
                    <label className="label-produto" htmlFor="image">Imagem do produto</label>
                    <input className="input-produto-image" id="file-image" type="file" accept="image/*"  onChange={handleChangeImage} />
                    <label htmlFor="file-image" className="custom-button">Clique aqui para selecionar uma imagem</label>
                    
        
                    <label className="label-produto" htmlFor="nome">Nome do produto</label>
                    <input className="input-produto"  type="text" id="nome" name="nome" value={nome} onChange={(e) => setNome(e.target.value)} placeholder="Digite o nome do produto" required /> 
                
                    <label className="label-produto" htmlFor="categoria">Categoria</label>
                    <select className="select-produto" id="categoria" name="categoria" value={categoria} onChange={handleChange}>
                        <option value="Eletrônicos">Eletrônicos</option>
                        <option value="Roupas">Roupas</option>
                        <option value="Casa e Jardim">Casa e Jardim</option>
                        <option value="Esportes">Esportes</option> 
                        <option value="Livros">Livros</option>  
                        <option value="Brinquedos">Brinquedos</option>
                        <option value="Alimentos">Alimentos</option> 
                        <option value="Outros">Outros</option>
                    </select>  

                    <label className="label-produto" htmlFor="status">Status</label>
                    <select className="select-produto" id="status" name="status" value={status} onChange={handleChange}>
                        <option value="Disponível">Disponível</option>
                        <option value="Indisponível">Indisponível</option> 
                    </select>

                    <label className="label-produto"  htmlFor="preco">Preço do produto</label>
                    <input className="input-produto" type="number" id="preco" name="preco" value={preco} onChange={(e) => setPreco(e.target.value)} placeholder="Digite o preço do produto" required />
    
                    <label className="label-produto"  htmlFor="descricao">Descrição do produto</label>
                    <textarea className="textarea-produto" id="descricao" name="descricao" value={descricao} onChange={(e) => setDescricao(e.target.value)} placeholder="Digite a descrição do produto" required></textarea> 

                <input type="submit" value="Cadastrar Produto" className="btn-cadastrar-produto" />
        
        </form>
    </div> 
    </>
  );
}
