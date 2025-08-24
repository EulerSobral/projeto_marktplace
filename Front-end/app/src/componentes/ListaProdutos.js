import React, {useState, useEffect} from "react";  
import {Link} from "react-router-dom";
import './ListaProdutos.css';
import axios from "axios";

export default function ListaProdutos() {
  const [produtoNome, setProdutoNome] = useState(""); 
  const [produtoCategoria, setProdutoCategoria] = useState("");
  const [produtoStatus, setProdutoStatus] = useState(""); 
  
  const [produtos, setProdutos] = useState([]);

  const [timerId, setTimerId] = useState(null);

    const handleMouseEnter = () => {
        const id = setTimeout(() => {
            alert('Tá esperando o quê? Boraa moeer!! 🚀');
        }, 7000);
        setTimerId(id);
    }; 
    const handleMouseLeave = () => {
        if (timerId) {
            clearTimeout(timerId);
            setTimerId(null);
        }
    };
  useEffect(() => {
    handleFilterChange();
  }, []);


  const handleFilterChange = async (e) => {  
      if (e) e.preventDefault();
      try {
      const response = await axios.get("http://localhost:8081/produtos", {  
        params: {
        nome_produto: produtoNome || undefined,
        categoria: produtoCategoria === "Todos" ? undefined : produtoCategoria,
        status: produtoStatus === "Todos" ? undefined : produtoStatus
      }
    });  
      setProdutos(response.data);
    } catch (error) {
      console.error("Erro ao buscar produtos:", error);
    }
    }

  return (
    <> 
      <header className="header-lista-produto"> 
              <div className="div-header">
                  <h1 className="h1-header">Lista de Produtos</h1>
                  <p className="p-header">Gerencie todos os produtos do sistema</p>
              </div> 
              <p className="p-link-header-produto"><Link to="/cadastrar-produtos" onMouseEnter={handleMouseEnter} onMouseLeave={handleMouseLeave} className="link-header-produto">+ Novo Produto</Link></p>
      </header> 
      <div className="form-filtro-container">  
        <p className="form-title">Filtros</p>
        <form className="form-filtro" onSubmit={handleFilterChange}> 
            <input className="input-filtro" type="text" placeholder="Buscar por nome" value={produtoNome} onChange={(e) => setProdutoNome(e.target.value)} />
            <select className="select-filtro" value={produtoCategoria} onChange={(e) => setProdutoCategoria(e.target.value)}>
                <option value="Todos">Todas as Categorias</option>
                <option value="Eletrônicos">Eletrônicos</option>
                <option value="Roupas">Roupas</option>
                <option value="Livros">Livros</option>
                <option value="Móveis">Móveis</option>
            </select>
            <select className="select-filtro" value={produtoStatus} onChange={(e) => setProdutoStatus(e.target.value)}>
                <option value="Todos">Todos os Status</option>
                <option value="Disponível">Disponível</option>
                <option value="Indisponível">Indisponível</option>
            </select>
            <button type="submit" className="btn-filtrar">Filtrar</button>
        </form> 
      </div> 
      <div className="lista-produtos-section"> 
          {produtos.length === 0 ? (
            <p className="no-products-message">Nenhum produto encontrado.</p>
          ) : (
            <div className="tabela-produtos"> 
              {produtos.map((produto) => (
              <div className="produto-content" key={produto.id}>
                    <img src={`data:image/jpeg;base64,${produto.imagem}`} alt={produto.nome} className="produto-imagem" /> 
                    <div className="produto-info">
                      <h2 className="produto-nome">{produto.nome_produto} </h2> 
                      <p className="produto-status">{produto.status}</p>
                    </div>
                    <p className="produto-categoria">{produto.categoria}</p>
                    <p className="produto-preco">R$ {produto.preco}</p>
                    <p className="produto-descricao">{produto.descricao}</p>
               </div>
                ))}
            </div>
          )}
      </div>
    </>
  );
}