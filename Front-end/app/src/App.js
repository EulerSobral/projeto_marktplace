import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom'; 
import Login from './componentes/Login'; // Changed from TelaLogin to Login to match your current import
import CadastroUsuario from './componentes/CadastroUsuario';  
import CadastrarProdutos from './componentes/CadastrarProdutos';
import ListaProdutos from './componentes/ListaProdutos'; 

import './App.css';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/cadastro" element={<CadastroUsuario />} /> 
        <Route path="/cadastrar-produtos" element={<CadastrarProdutos />} />
        <Route path="/produtos" element={<ListaProdutos />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;