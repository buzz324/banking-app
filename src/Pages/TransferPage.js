import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function TransferPage() {
  const { userId } = useParams();
  const navigate = useNavigate();
  const [amount, setAmount] = useState('');
  const [fromAccount, setFromAccount] = useState('');
  const [toAccount, setToAccount] = useState('');
  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    // Fetch accounts for the user
    fetch(`http://localhost:8080/api/accounts?userId=${userId}`)
      .then(response => response.json())
      .then(data => {
        if (Array.isArray(data)) {
          setAccounts(data);
        } else {
          console.error('Fetched data is not an array:', data);
        }
      })
      .catch(error => console.error('Error fetching accounts:', error));
  }, [userId]);

  const handleTransfer = () => {
    const transferRequest = {
      fromAccountId: fromAccount,
      toAccountId: toAccount,
      amount: parseFloat(amount)
    };

    fetch('http://localhost:8080/api/transfer', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(transferRequest)
    })
      .then(response => response.text())
      .then(data => {
        console.log(data);
        // navigate(`/accounts/${userId}`);
      })
      .catch(error => console.error('Error performing transfer:', error));
  };

  return (
    <div>
      <h2>Transfer Funds</h2>
      <div>
        <label>
          From Account:
          <select value={fromAccount} onChange={(e) => setFromAccount(e.target.value)}>
            <option value="">Select Account</option>
            {accounts.map(account => (
              <option key={account.id} value={account.id}>
                {account.accountType} - Balance: {account.balance}
              </option>
            ))}
          </select>
        </label>
      </div>
      <div>
        <label>
          To Account:
          <select value={toAccount} onChange={(e) => setToAccount(e.target.value)}>
            <option value="">Select Account</option>
            {accounts.map(account => (
              <option key={account.id} value={account.id}>
                {account.accountType} - Balance: {account.balance}
              </option>
            ))}
          </select>
        </label>
      </div>
      <div>
        <label>
          Amount:
          <input type="number" value={amount} onChange={(e) => setAmount(e.target.value)} />
        </label>
      </div>
      <button onClick={handleTransfer}>Transfer</button>
      <button onClick={() => navigate(-1)}>Back</button>
    </div>
  );
}

export default TransferPage;