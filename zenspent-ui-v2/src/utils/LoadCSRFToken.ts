import axios from 'axios'

export function loadCSRFToken() {
  axios.defaults.withXSRFToken = true
  axios.defaults.withCredentials = true
  const appMode = import.meta.env.VITE_APP_MODE
  console.log('appMode: ' + appMode)
  console.log('sessionStorage: ', sessionStorage)
  if (appMode != null && appMode == 'development') {
    axios.get('/api/v1/internal/csrf-token')
  }
}
