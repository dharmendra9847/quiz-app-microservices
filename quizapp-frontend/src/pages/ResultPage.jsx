import React, { useEffect, useRef, useState } from 'react';
import { useQuiz } from '../context/QuizContext';
import Button from '../components/Button';
import './ResultPage.css';

const getGrade = (pct) => {
  if (pct >= 90) return { label: 'Outstanding!', emoji: '🏆', color: 'var(--accent-yellow)', msg: "You're a master of this topic!" };
  if (pct >= 75) return { label: 'Excellent!', emoji: '🌟', color: 'var(--accent-green)', msg: 'Strong performance. Keep it up!' };
  if (pct >= 60) return { label: 'Good Job!', emoji: '👍', color: 'var(--accent-primary)', msg: 'Solid effort! A bit more practice and you\'ll ace it.' };
  if (pct >= 40) return { label: 'Keep Practicing', emoji: '📚', color: 'var(--accent-yellow)', msg: 'You\'re on the right track. Review and retry!' };
  return { label: 'Needs Work', emoji: '💪', color: 'var(--accent-red)', msg: 'Don\'t give up! Every attempt makes you stronger.' };
};

const ResultPage = () => {
  const { state, resetQuiz, setView } = useQuiz();
  const { result, activeQuiz } = state;
  const { score, total, timeTaken } = result;
  const { title, category } = activeQuiz;

  const pct = total > 0 ? Math.round((score / total) * 100) : 0;
  const grade = getGrade(pct);

  const [displayScore, setDisplayScore] = useState(0);
  const [displayPct, setDisplayPct] = useState(0);
  const circleRef = useRef(null);

  // Animate score counter
  useEffect(() => {
    const duration = 1200;
    const start = Date.now();
    const tick = () => {
      const progress = Math.min((Date.now() - start) / duration, 1);
      const ease = 1 - Math.pow(1 - progress, 3);
      setDisplayScore(Math.round(ease * score));
      setDisplayPct(Math.round(ease * pct));
      if (progress < 1) requestAnimationFrame(tick);
    };
    const raf = requestAnimationFrame(tick);
    return () => cancelAnimationFrame(raf);
  }, [score, pct]);

  // SVG circle progress
  const radius = 70;
  const circumference = 2 * Math.PI * radius;
  const strokeOffset = circumference - (pct / 100) * circumference;

  const formatTime = (s) => {
    if (!s) return '—';
    const m = Math.floor(s / 60);
    const sec = s % 60;
    return m > 0 ? `${m}m ${sec}s` : `${sec}s`;
  };

  return (
    <div className="result-page page-enter">
      <div className="container">
        {/* Confetti-like decorative dots */}
        <div className="result-confetti" aria-hidden="true">
          {Array.from({ length: 20 }).map((_, i) => (
            <div
              key={i}
              className="confetti-dot"
              style={{
                left: `${Math.random() * 100}%`,
                top: `${Math.random() * 100}%`,
                width: `${Math.random() * 8 + 4}px`,
                height: `${Math.random() * 8 + 4}px`,
                animationDelay: `${Math.random() * 2}s`,
                background: [
                  'var(--accent-primary)',
                  'var(--accent-secondary)',
                  'var(--accent-green)',
                  'var(--accent-yellow)',
                ][Math.floor(Math.random() * 4)],
              }}
            />
          ))}
        </div>

        <div className="result-content">
          {/* Score Circle */}
          <div className="score-circle-wrapper animate-scale-in">
            <div className="score-grade-emoji">{grade.emoji}</div>
            <svg className="score-ring" width="180" height="180" viewBox="0 0 180 180">
              <circle cx="90" cy="90" r={radius} fill="none" stroke="var(--border-subtle)" strokeWidth="10" />
              <circle
                ref={circleRef}
                cx="90" cy="90" r={radius}
                fill="none"
                stroke={grade.color}
                strokeWidth="10"
                strokeLinecap="round"
                strokeDasharray={circumference}
                strokeDashoffset={strokeOffset}
                transform="rotate(-90 90 90)"
                style={{ transition: 'stroke-dashoffset 1.2s cubic-bezier(0.4,0,0.2,1)' }}
              />
              <text x="90" y="82" textAnchor="middle" fill="var(--text-primary)" fontSize="28" fontFamily="'Syne', sans-serif" fontWeight="800">
                {displayPct}%
              </text>
              <text x="90" y="104" textAnchor="middle" fill="var(--text-muted)" fontSize="12" fontFamily="'DM Sans', sans-serif">
                Score
              </text>
            </svg>
          </div>

          {/* Grade Banner */}
          <div className="grade-banner animate-slide-up" style={{ '--grade-color': grade.color }}>
            <h1 className="grade-banner__label">{grade.label}</h1>
            <p className="grade-banner__msg">{grade.msg}</p>
          </div>

          {/* Stats Grid */}
          <div className="result-stats animate-slide-up">
            {[
              { label: 'Correct Answers', value: `${displayScore} / ${total}`, icon: '✓', color: 'var(--accent-green)' },
              { label: 'Accuracy', value: `${displayPct}%`, icon: '🎯', color: 'var(--accent-primary)' },
              { label: 'Time Taken', value: formatTime(timeTaken), icon: '⏱', color: 'var(--accent-yellow)' },
              { label: 'Category', value: category || '—', icon: '📘', color: 'var(--accent-secondary)' },
            ].map((item, i) => (
              <div key={i} className="result-stat glass-card" style={{ '--stat-color': item.color }}>
                <span className="result-stat__icon">{item.icon}</span>
                <span className="result-stat__value">{item.value}</span>
                <span className="result-stat__label">{item.label}</span>
              </div>
            ))}
          </div>

          {/* Quiz title */}
          {title && (
            <div className="result-quiz-title animate-fade-in">
              Quiz: <strong>{title}</strong>
            </div>
          )}

          {/* Performance Bar */}
          <div className="performance-bar glass-card animate-slide-up">
            <h3 className="performance-bar__title">Performance Breakdown</h3>
            <div className="perf-bar__track">
              <div
                className="perf-bar__fill"
                style={{ width: `${pct}%`, background: grade.color }}
              />
            </div>
            <div className="perf-bar__labels">
              <span>0%</span>
              <span style={{ color: grade.color }}>{pct}%</span>
              <span>100%</span>
            </div>
            <div className="perf-bar__markers">
              {[
                { pct: 40, label: 'Pass' },
                { pct: 60, label: 'Good' },
                { pct: 75, label: 'Excel' },
                { pct: 90, label: 'Master' },
              ].map(m => (
                <div key={m.pct} className="perf-marker" style={{ left: `${m.pct}%` }}>
                  <div className="perf-marker__line" />
                  <span className="perf-marker__label">{m.label}</span>
                </div>
              ))}
            </div>
          </div>

          {/* Actions */}
          <div className="result-actions animate-slide-up">
            <Button size="lg" onClick={resetQuiz}>Try Again 🔁</Button>
            <Button variant="secondary" size="lg" onClick={() => setView('questions')}>New Quiz →</Button>
            <Button variant="ghost" size="lg" onClick={() => setView('home')}>Home</Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ResultPage;
