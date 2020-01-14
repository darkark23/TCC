import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderPrincipalComponent } from './header-principal/header-principal.component';
import { PrincipalComponent } from './principal/principal.component';
import { PrincipalService } from './principal/principal.service';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderPrincipalComponent,
    PrincipalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [PrincipalService],
  bootstrap: [AppComponent]
})
export class AppModule { }
