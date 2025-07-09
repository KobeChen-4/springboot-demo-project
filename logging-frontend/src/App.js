import React, { useState } from 'react';

function App() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [loginMessage, setLoginMessage] = useState('');
  const [users, setUsers] = useState([]);

  const handleLogin = async () => {
    try {
      const response = await fetch('http://localhost:8080/users/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: new URLSearchParams({
          username,
          password,
        }),
      });
      const text = await response.text();
      setLoginMessage(text);  // ✅ Always show actual backend response
      console.log("Server response:", text);
    } catch (error) {
      setLoginMessage('Login failed.');
      console.error("Fetch error:", error);
    }
  };



  const loadUsers = async () => {
    try {
      const response = await fetch('http://localhost:8080/users');
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      alert('Failed to fetch users.');
    }
  };

  return (
      <div style={{ padding: '2rem' }}>
        <h1>📝 Logging Demo Frontend</h1>

        <div>
          <h2>Login</h2>
          <input
              placeholder="Username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
          />
          <input
              placeholder="Password"
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
          />
          <button onClick={handleLogin}>Login</button>
          <p>{loginMessage}</p>
        </div>

        <div>
          <h2>Users</h2>
          <button onClick={loadUsers}>Load All Users</button>
          <ul>
            {users.map((user) => (
                <li key={user.id}>{user.name} ({user.password})</li>
            ))}
          </ul>
        </div>
      </div>
  );
}

export default App;

