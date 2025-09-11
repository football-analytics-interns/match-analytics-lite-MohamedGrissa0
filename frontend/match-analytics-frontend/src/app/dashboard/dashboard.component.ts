import { Component, signal, computed } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ChartConfiguration, ChartType } from 'chart.js';
import { NgChartsModule } from 'ng2-charts';

interface Match {
  id: number;
  date: string;
  homeTeam: string;
  awayTeam: string;
  homeScore: number;
  awayScore: number;
}

interface Player {
  id: number;
  name: string;
  team: string;
  position: string;
}

interface Event {
  minute: number;
  type: string;
  playerId: number;
  meta: any;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, HttpClientModule, NgChartsModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
 
  match = signal<Match | null>(null);
  players = signal<Player[]>([]);
  events = signal<Event[]>([]);
  loading = signal(true);
  error = signal<string | null>(null);

  playerStats = computed(() => {
    if (!this.players().length || !this.events().length) return [];
  
    return this.players().map(p => {
      const goals = this.events().filter(e => e.type === 'GOAL' && e.playerId === p.id).length;
      const assists = this.events().filter(
        e => e.type === 'GOAL' && e.meta?.assistId === p.id 
      ).length;
      const shots = this.events().filter(
        e => e.type === 'SHOT' && e.playerId === p.id 
      ).length;
      const tackles = this.events().filter(
        e => e.type === 'TACKLE' && e.playerId === p.id 
      ).length;
      const rating = 6 + goals * 1.5 + assists*1 + tackles*0.5 + shots*0.3; // simplified formula
      return { ...p, goals, assists,shots,tackles, rating: rating.toFixed(1) };
    });
  });


  chartData: ChartConfiguration<'bar'>['data'] = {
    labels: [],
    datasets: [
      { label: 'Goals', data: [], backgroundColor: '#4caf50' },
      { label: 'Assists', data: [], backgroundColor: '#2196f3' },
      { label: 'Shots', data: [], backgroundColor: '#13aff0' },
      { label: 'Tackles', data: [], backgroundColor: '#22ff00' },

    ]
  };
  chartType: ChartType = 'bar';

  constructor(private http: HttpClient) {
    this.loadData();
  }

  loadData() {
    this.loading.set(true);
    this.error.set(null);

    Promise.all([
      this.http.get<Match>('http://localhost:8080/api/match/1').toPromise(),
      this.http.get<Player[]>('http://localhost:8080/api/player').toPromise(),
      this.http.get<Event[]>('http://localhost:8080/api/event').toPromise()
    ])
      .then(([match, players, events]) => {
        if (match) this.match.set(match);
        if (players) this.players.set(players);
        if (events) this.events.set(events);

        const stats = this.playerStats();
        this.chartData.labels = stats.map(p => p.name);
        this.chartData.datasets[0].data = stats.map(p => p.goals);
        this.chartData.datasets[1].data = stats.map(p => p.assists);
        this.chartData.datasets[2].data = stats.map(p => p.shots);
        this.chartData.datasets[3].data = stats.map(p => p.tackles);
      })
      .catch(err => {
        console.error(err);
        this.error.set('⚠️ Failed to load match data. Please try again later.');
      })
      .finally(() => this.loading.set(false));
  }
}
