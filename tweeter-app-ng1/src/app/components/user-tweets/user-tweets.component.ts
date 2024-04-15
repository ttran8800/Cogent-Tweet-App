import { Component, OnInit, OnDestroy } from '@angular/core';
import { DataService } from 'src/app/services/data.service';
import { ClockService } from 'src/app/services/clock.service';
import { IUser } from 'src/app/models/user.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-tweets',
  templateUrl: './user-tweets.component.html',
  styleUrls: ['./user-tweets.component.css']
})
export class UserTweetsComponent implements OnInit, OnDestroy{

  user: IUser | null = null;
  currentTime?: Date;
  private subscription: Subscription = new Subscription();

  constructor(private dataService: DataService,
    private clockService: ClockService) { }

    ngOnInit(): void {
        this.subscription.add(
          this.dataService.user$.subscribe(user => {
            this.user = user;
          })
        );
        this.subscription.add(
          this.clockService.getClock().subscribe((time => {
            this.currentTime = time;
          }))
        );
    }

    ngOnDestroy(): void {
        this.subscription.unsubscribe();
    }
}
