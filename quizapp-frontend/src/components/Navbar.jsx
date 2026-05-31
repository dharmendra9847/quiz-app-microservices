import React, { useState, useEffect } from 'react';
import { useQuiz } from '../context/QuizContext';
import './Navbar.css';

const Navbar = () => {
  const { state, setView } = useQuiz();
  const [scrolled, setScrolled] = useState(false);
  const [mobileOpen, setMobileOpen] = useState(false);

  useEffect(() => {
    const onScroll = () => setScrolled(window.scrollY > 20);
    window.addEventListener('scroll', onScroll);
    return () => window.removeEventListener('scroll', onScroll);
  }, []);

  const navLinks = [
    { label: 'Home', view: 'home' },
    { label: 'Take Quiz', view: 'questions' },
    { label: 'Admin', view: 'admin' },
  ];

  return (
    <nav className={`navbar ${scrolled ? 'navbar--scrolled' : ''}`}>
      <div className="navbar__inner container">
        <button className="navbar__logo" onClick={() => setView('home')}>
          <span className="navbar__logo-icon">⚡</span>
          <span className="navbar__logo-text">Quiz<span className="gradient-text">App</span></span>
        </button>

        <div className={`navbar__links ${mobileOpen ? 'navbar__links--open' : ''}`}>
          {navLinks.map((link) => (
            <button
              key={link.view}
              className={`navbar__link ${state.view === link.view ? 'navbar__link--active' : ''}`}
              onClick={() => { setView(link.view); setMobileOpen(false); }}
            >
              {link.label}
              {state.view === link.view && <span className="navbar__link-dot" />}
            </button>
          ))}
        </div>

        <button className="navbar__mobile-toggle" onClick={() => setMobileOpen(!mobileOpen)}>
          <span className={`hamburger ${mobileOpen ? 'hamburger--open' : ''}`}>
            <span /><span /><span />
          </span>
        </button>
      </div>
    </nav>
  );
};

export default Navbar;
