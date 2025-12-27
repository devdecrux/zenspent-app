import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevTools from 'vite-plugin-vue-devtools'
import tailwindcss from '@tailwindcss/vite'
import path from 'node:path'
import colors from 'picocolors'

const traefikUrl = process.env.VITE_TRAEFIK_URL ?? 'http://localhost'

export default defineConfig({
  base: '/frontend/',

  plugins: [
    vue(),
    vueDevTools(),
    tailwindcss(),
    {
      name: 'traefik-info',
      configureServer(server) {
        const originalPrintUrls = server.printUrls
        server.printUrls = () => {
          originalPrintUrls()
          server.config.logger.info(
            `  ${colors.green('➜')}  ${colors.bold('Primary Access URL')}: ${colors.cyan(traefikUrl)}`,
          )
        }
      },
    },
  ],

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
