import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

function AccountsPage() {
  const { userId } = useParams();
  const [accounts, setAccounts] = useState([]);
  const navigate = useNavigate();
  const [amount, setAmount] = useState('');
  const [fromAccount, setFromAccount] = useState('');
  const [toAccount, setToAccount] = useState('');

  const handleTransfer = () => {
    // Perform the transfer logic here
    console.log(`Transferring ${amount} from ${fromAccount} to ${toAccount} for user ${userId}`);
    // Navigate back to the accounts page after transfer
    navigate(`/accounts/${userId}`);
  };

  useEffect(() => {
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

  return (
    <div>
      <h2>Accounts</h2>
      <div>
        {accounts.map(account => (
          <div key={account.id}>
            <p>Account Type: {account.accountType}</p>
            <p>Balance: {account.balance}</p>
          </div>
        ))}
      </div>
      <button onClick={() => navigate(-1)}>Home button</button>
      <button onClick={() => navigate(`/transfer/${userId}`)}>Transfer money</button>
      <button onClick={() => navigate(-1)}>See Transactions</button>
    </div>
  );
}

export default AccountsPage;