import React, { useState } from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import GraduateAcc from './accounts/GraduateAcc'
import SavingAcc from './accounts/SavingsAcc'
import AccountsPage from './accounts/AccountsPage'
import TransferPage from './accounts/TransferPage'
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
            <Route path='/graduate' element={<GraduateAcc />} />
            <Route path='/savings' element={<SavingAcc />} />
          </Routes>
        </div>
      </div>
    </BrowserRouter>
  )
}

export default App