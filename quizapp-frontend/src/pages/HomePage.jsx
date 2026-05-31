import React, { useEffect, useRef } from 'react';
import { useQuiz } from '../context/QuizContext';
import Button from '../components/Button';
import './HomePage.css';

const CATEGORIES = [
  { name: 'Java', icon: '☕', color: '#f97316', desc: 'Core Java, OOP, Collections' },
  { name: 'Python', icon: '🐍', color: '#22d3a1', desc: 'Scripting, Data, ML basics' },
  { name: 'JavaScript', icon: '⚡', color: '#fbbf24', desc: 'ES6+, DOM, Async patterns' },
  { name: 'SQL', icon: '🗄️', color: '#7c6af7', desc: 'Queries, Joins, Optimization' },
  { name: 'Spring', icon: '🌱', color: '#22d3a1', desc: 'Spring Boot, REST, JPA' },
  { name: 'React', icon: '⚛️', color: '#38bdf8', desc: 'Components, Hooks, State' },
];

const STATS = [
  { value: '500+', label: 'Questions' },
  { value: '12', label: 'Categories' },
  { value: '10k+', label: 'Quizzes Taken' },
  { value: '4.9★', label: 'Avg Rating' },
];

const HomePage = () => {
  const { setView } = useQuiz();
  const heroRef = useRef(null);

  useEffect(() => {
    const items = heroRef.current?.querySelectorAll('.stagger-item');
    items?.forEach((el, i) => {
      el.style.animationDelay = `${i * 0.1}s`;
    });
  }, []);

  return (
    <div className="home-page page-enter" ref={heroRef}>
      {/* Hero */}
      <section className="hero">
        <div className="hero__badge stagger-item animate-slide-up">
          <span className="hero__badge-dot" />
          Level up your knowledge
        </div>
        <h1 className="hero__title stagger-item animate-slide-up">
          Master Any Topic<br />
          <span className="gradient-text">One Quiz at a Time</span>
        </h1>
        <p className="hero__subtitle stagger-item animate-slide-up">
          Challenge yourself with curated questions across Java, Python, SQL, React and more.
          Track your progress, beat your score, become unstoppable.
        </p>
        <div className="hero__actions stagger-item animate-slide-up">
          <Button size="lg" onClick={() => setView('questions')}>
            Start Quiz Now →
          </Button>
          <Button variant="secondary" size="lg" onClick={() => setView('admin')}>
            Manage Questions
          </Button>
        </div>

        {/* Floating cards decoration */}
        <div className="hero__floaters" aria-hidden="true">
          <div className="floater floater--1 animate-float">✓ Java Basics</div>
          <div className="floater floater--2 animate-float" style={{animationDelay:'1s'}}>🔥 Streak: 7</div>
          <div className="floater floater--3 animate-float" style={{animationDelay:'0.5s'}}>⭐ 92%</div>
        </div>
      </section>

      {/* Stats */}
      <section className="stats">
        <div className="container">
          <div className="stats__grid">
            {STATS.map((s, i) => (
              <div key={i} className="stat-card glass-card animate-slide-up" style={{ animationDelay: `${i * 0.08}s` }}>
                <span className="stat-card__value gradient-text">{s.value}</span>
                <span className="stat-card__label">{s.label}</span>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* Categories */}
      <section className="categories">
        <div className="container">
          <div className="section-header">
            <h2 className="section-title">Browse Categories</h2>
            <p className="section-subtitle">Pick a subject and dive into focused practice</p>
          </div>
          <div className="categories__grid">
            {CATEGORIES.map((cat, i) => (
              <button
                key={cat.name}
                className="category-card animate-slide-up"
                style={{ animationDelay: `${i * 0.07}s`, '--cat-color': cat.color }}
                onClick={() => setView('questions')}
              >
                <span className="category-card__icon">{cat.icon}</span>
                <span className="category-card__name">{cat.name}</span>
                <span className="category-card__desc">{cat.desc}</span>
                <span className="category-card__arrow">→</span>
              </button>
            ))}
          </div>
        </div>
      </section>

      {/* How it works */}
      <section className="how-it-works">
        <div className="container">
          <div className="section-header">
            <h2 className="section-title">How It Works</h2>
          </div>
          <div className="steps__grid">
            {[
              { num: '01', title: 'Choose Category', desc: 'Pick from Java, Python, SQL, React and more.' },
              { num: '02', title: 'Set Quiz Parameters', desc: 'Decide number of questions and give your quiz a title.' },
              { num: '03', title: 'Answer & Submit', desc: 'Work through questions at your own pace and submit.' },
              { num: '04', title: 'See Your Score', desc: 'Get instant results with detailed performance breakdown.' },
            ].map((step, i) => (
              <div key={i} className="step animate-slide-up" style={{ animationDelay: `${i * 0.1}s` }}>
                <span className="step__num gradient-text">{step.num}</span>
                <h3 className="step__title">{step.title}</h3>
                <p className="step__desc">{step.desc}</p>
              </div>
            ))}
          </div>
        </div>
      </section>

      {/* CTA */}
      <section className="cta">
        <div className="container">
          <div className="cta__card glass-card">
            <h2 className="cta__title">Ready to test your skills?</h2>
            <p className="cta__desc">Join thousands of learners who use QuizApp to sharpen their knowledge.</p>
            <Button size="lg" onClick={() => setView('questions')}>
              Create a Quiz →
            </Button>
          </div>
        </div>
      </section>
    </div>
  );
};

export default HomePage;
