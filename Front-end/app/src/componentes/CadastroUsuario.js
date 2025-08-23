import React, {useState} from 'react';  
import { Link, useNavigate } from 'react-router-dom'; 
import axios from "axios"
import './UsuarioCampusStyle.css'; 

function CadastroUsuario() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState(""); 
  const navigate = useNavigate();

  const handleSubmit = async(e) => { 
      e.preventDefault(); 
  
      try {
        await axios.post("http://localhost:8081/cadastro",  {
          nome: nome,
          email: email,
          password: senha
        }); 
        navigate("/produtos");
      } catch (error) { 
        alert("Erro ao cadastrar usuário. Tente novamente.");
        console.error(error);
      }
      
  }
  return (
    <div className="div-user"> 
      <form className='form-user' onSubmit={handleSubmit}> 
          <h1 className='title-user'>Cadastro de Usuário</h1>
          <p className='p-user'>Cadastre-se para acessar o sistema.</p> 
         <label className='label-user' htmlFor="nome">Nome</label> 
         <input className='input-user' type="text" id="nome" name="nome" value={nome} onChange={(e) => setNome(e.target.value)} placeholder='Seu nome completo' required /> 
        
         <label className='label-user'  htmlFor="email">Email</label> 
         <input className='input-user' type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder='digite seu email' name="email" required />  
         
         <label className='label-user' htmlFor="senha">Senha</label> 
         <input className='input-user' type="password" id="senha" value={senha} onChange={(e) => setSenha(e.target.value)} placeholder='digite sua senha' name="senha" required />
        
        <input  type="submit" value="Cadastrar" className='btn-cadastrar' />
        <p className='p-user'>Já tem uma conta ? <Link to="/" className='link-user'>Faça login aqui</Link></p>
      </form>  
    </div>
  );
}

export default CadastroUsuario;