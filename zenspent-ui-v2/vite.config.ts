import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
import tailwindcss from '@tailwindcss/vite'
import path from 'node:path'

export default defineConfig({
  base: '/frontend/',

  plugins: [vue(), vueDevTools(), tailwindcss()],

  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },

  build: {
    outDir: 'dist',
  },

  server: {
    host: '0.0.0.0',
    port: 5173,
    origin: 'http://localhost',
    allowedHosts: ['host.docker.internal'],
    proxy: {
      '/api': {
        target: 'http://host.docker.internal:8081',
        changeOrigin: true,
        secure: false,
        cookieDomainRewrite: 'localhost',
      },
    },
  },
})
