import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
import tailwindcss from '@tailwindcss/vite'
import path from 'node:path'

// https://vite.dev/config/
export default defineConfig({
  base: '/frontend/',
  plugins: [vue(), vueDevTools(), tailwindcss()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
  build: {
    outDir: 'frontend',
  },
  server: {
    proxy: {
      '/api': {
        // TODO: dynamic target based on environment variable
        target: 'http://192.168.0.2/api',
        changeOrigin: true,
        rewrite: (path) => path,
      },
    },
  },
})
