import React, {useState} from 'react';  
import { Link , useNavigate} from 'react-router-dom';  
import axios from "axios"
import './UsuarioCampusStyle.css';  

export default function Login() { 
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async(e) => { 
      e.preventDefault(); 
  
      try {
       await axios.post("http://localhost:8081/",  {
          email: email,
          password: senha
        });  
        navigate("/produtos");
      } catch (error) {  
        alert("Erro ao fazer login. Verifique suas credenciais.");
        console.error(error);
      }
      
  }
  return ( 
    <div className="div-user"> 

      <form className='form-user' onSubmit={handleSubmit}> 
        <h1 className='title-user'>Login do Usuário</h1>
         <p className='p-user'>Faça seu login para acessar o sistema.</p>
        
         <label className='label-user'  htmlFor="email">Email</label> 
         <input className='input-user' type="email" id="email" value={email} onChange={(e) => setEmail(e.target.value)} placeholder='digite seu email' name="email" required />  
         
         <label className='label-user' htmlFor="senha">Senha</label> 
         <input className='input-user' type="password" id="senha" value={senha} onChange={(e) => setSenha(e.target.value)} placeholder='digite sua senha' name="senha" required />
        
        <input  type="submit" value="Login" className='btn-cadastrar' href="/produtos" />
        <p className='p-user'>Não tem uma conta ? <Link to="/cadastro" className='link-user'>Faça seu cadastro aqui</Link></p>
      </form>  
    </div> 
  );
}
