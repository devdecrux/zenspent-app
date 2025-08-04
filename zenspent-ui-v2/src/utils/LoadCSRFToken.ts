import axios from 'axios'

export function loadCSRFToken() {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  axios.get('/api/v1/internal/csrf-token')
}
