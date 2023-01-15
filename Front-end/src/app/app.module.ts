import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { MatSliderModule } from '@angular/material/slider';
import { MatStepperModule } from '@angular/material/stepper';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { WebcamModule } from 'ngx-webcam';
import { AgentModule } from './module/agent/agent.module';
import { ClientModule } from './module/client/client.module';
import { AgentRoutingModule } from './module/agent/agent-routing.module';
import { ClientRoutingModule } from './module/client/client-routing.module';
import { LoginreuseComponent } from './component/loginreuse/loginreuse.component';


@NgModule({
    declarations: [
        AppComponent,
        LoginreuseComponent,
    ],
    providers: [

    ],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        ClientRoutingModule,
        AgentRoutingModule,
        AppRoutingModule,
        MatSliderModule,
        HttpClientModule,
        AgentModule,
        ClientModule,
        WebcamModule,
        MatStepperModule,
    ]
})
export class AppModule { }
