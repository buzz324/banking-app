import React, { useState, useEffect } from 'react'
import { useNavigate } from 'react-router-dom'

function Home() {
  const [users, setUsers] = useState([])
  const navigate = useNavigate();

  useEffect(() => {
    fetch('http://localhost:8080/api/users', {
      method: "GET",
      mode: "cors",
      headers: {
        "Content-Type": "application/json"
      }
    })
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`)
        }
        return response.json()
      })
      .then(data => {
        if (Array.isArray(data)) {
          setUsers(data)
        } else {
          console.error('Fetched data is not an array:', data)
        }
      })
      .catch(error => console.error('Error fetching users:', error))
  }, [])

  const handleUserClick = (userId) => {
    navigate(`/accounts/${userId}`);
  };

  return (
    <div>
      <h2>Users</h2>
      <div>
        {users.map(user => (
          <div key={user.id}>
            <button key = {user.id} onClick={() => handleUserClick(user.id)}> {user.name} </button>
          </div>
        ))}
      </div>
    
    </div>
  )
}

export default Home