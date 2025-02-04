const express = require('express')
const { Client } = require('pg')
const cors = require('cors')

const app = express()
const port = 4000

app.use(cors())
app.use(express.json())

const client = new Client({
  user: 'postgres',
  host: 'localhost',
  database: 'transaction-db',
  password: 12345,
  port: 5432
})

client.connect()
  .then(() => console.log('Connected to PostgreSQL'))
  .catch(err => console.error('Connection error', err.stack))

// Example endpoint to get data
app.get('/api/data', async (req, res) => {
  try {
    const result = await client.query('SELECT * FROM your_table')
    res.json(result.rows)
  } catch (err) {
    console.error(err)
    res.status(500).send('Server error')
  }
})

app.listen(port, () => {
  console.log(`Server running on port ${port}`)
})
