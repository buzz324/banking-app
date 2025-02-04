import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import AccountsPage from './pages/AccountsPage'
import TransferPage from './pages/TransferPage'
import Home from './Home'
import './App.css'

function App () {

  return (
    <BrowserRouter>
      <div className='App'>
        <h1>Banking App</h1>
        <div className='page-body'>
          <Routes>
            <Route path='/' element={<Home />} />
            <Route path="/accounts/:userId" element={<AccountsPage />} />
            <Route path="/transfer/:userId" element={<TransferPage />} />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  )
}

export default App