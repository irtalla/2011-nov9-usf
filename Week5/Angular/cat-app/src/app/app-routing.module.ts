import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MycatsComponent } from './mycats/mycats.component';
import { ViewcatsComponent } from './viewcats/viewcats.component';
import { ManageuserComponent } from './manageuser/manageuser.component';

const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path:'home',
    component: HomeComponent
  },
  {
    path:'mycats',
    component: MycatsComponent
  },
  {
    path:'viewcats',
    component: ViewcatsComponent
  },
  {
    path:'manage',
    component: ManageuserComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
