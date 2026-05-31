import React from 'react';
import { Toaster } from 'react-hot-toast';
import { QuizProvider, useQuiz } from './context/QuizContext';
import Navbar from './components/Navbar';
import HomePage from './pages/HomePage';
import QuizSetupPage from './pages/QuizSetupPage';
import QuizPage from './pages/QuizPage';
import ResultPage from './pages/ResultPage';
import AdminPage from './pages/AdminPage';
import './styles/global.css';

const AppRouter = () => {
  const { state } = useQuiz();
  const { view } = state;

  const renderPage = () => {
    switch (view) {
      case 'home':      return <HomePage />;
      case 'questions': return <QuizSetupPage />;
      case 'quiz':      return <QuizPage />;
      case 'result':    return <ResultPage />;
      case 'admin':     return <AdminPage />;
      default:          return <HomePage />;
    }
  };

  return (
    <div className="app">
      {/* Background decorative blobs */}
      <div className="bg-blob bg-blob-1" />
      <div className="bg-blob bg-blob-2" />
      <div className="bg-blob bg-blob-3" />

      {/* Navbar: hide during active quiz */}
      {view !== 'quiz' && <Navbar />}

      <main style={{ position: 'relative', zIndex: 1 }}>
        {renderPage()}
      </main>

      <Toaster
        position="top-right"
        toastOptions={{
          style: {
            background: '#1c1c28',
            color: '#f0f0f8',
            border: '1px solid rgba(255,255,255,0.08)',
            borderRadius: '12px',
            fontSize: '0.88rem',
            boxShadow: '0 8px 32px rgba(0,0,0,0.4)',
          },
          success: { iconTheme: { primary: '#22d3a1', secondary: '#1c1c28' } },
          error: { iconTheme: { primary: '#f43f5e', secondary: '#1c1c28' } },
        }}
      />
    </div>
  );
};

function App() {
  return (
    <QuizProvider>
      <AppRouter />
    </QuizProvider>
  );
}

export default App;
