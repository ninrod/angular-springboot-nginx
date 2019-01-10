import { Component, OnInit } from '@angular/core';
import { UsersService } from './services/users';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private service: UsersService) { }
  title = 'frontend';
  users = this.service.getUsers().pipe(map(e => e[0]))

  ngOnInit() {
    this.users.subscribe(i => console.dir(i))
  }
}
