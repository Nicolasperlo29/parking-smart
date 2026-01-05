import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { User } from '../../interfaces/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  constructor(private http: HttpClient) { }

  private API = 'http://localhost:8080/api/auth';

  login(email: string, password: string) {
    return this.http.post<any>(`${this.API}/login`, { email, password });
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  get token() {
    return localStorage.getItem('token');
  }

  isLogged() {
    return !!this.token;
  }

  getProfile(): Observable<User> {
    return this.http.get<User>(`${this.API}/me`).pipe(
      tap(user => console.log('Perfil del usuario obtenido:', user))
    );
  }
}
