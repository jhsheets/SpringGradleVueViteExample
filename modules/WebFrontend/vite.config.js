import { defineConfig, loadEnv } from 'vite'
import vue from '@vitejs/plugin-vue'


export default ({ mode }) => {
    // Load environment variables in the .env.development files
    Object.assign(process.env, loadEnv(mode, process.cwd(), ''))

    return defineConfig({
        plugins: [vue()],

        // Use environment variables with the following prefix
        envPrefix: 'VITE_',

        // The server settings for when we run the WebFrontend dev server, during development
        server: {
            port: process.env.VITE_PORT,
            // Shutdown if above port is in use, instead of automatically finding a different port
            strictPort: true,
            // Setup proxy to forward requests to WebBackend
            proxy: {
              // Proxy any request with a path that starts with /api to our WebBackend
              '/api': process.env.VITE_PROXY_TO
            }
        }
    });
}