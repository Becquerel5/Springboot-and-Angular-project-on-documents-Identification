import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'
import { ApiModule, Configuration } from 'src/app/models';
import { MatStepperModule } from '@angular/material/stepper';
import { environment } from 'src/environments/environment';
import { PreviewComponent } from './preview/preview.component';
import { WebCamComponent } from './web-cam/web-cam.component';
import { WebcamModule } from 'ngx-webcam';
import { StepperHeaderComponent } from './stepper-header/stepper-header.component';
import { TranslateModule, TranslateLoader} from '@ngx-translate/core';
import { TranslateHttpLoader} from '@ngx-translate/http-loader';
import { HttpClient } from '@angular/common/http';
import { AddFilesComponent } from './add-files/add-files.component';
export function HttpLoaderFactory(http: HttpClient){
  return new TranslateHttpLoader(http);
}

export const getConfiguration = () => {
  return new Configuration({
    basePath: environment.basePath
  })
};

const MATERIAL= [
  MatStepperModule
]

@NgModule({
  declarations: [
    PreviewComponent,
    WebCamComponent,
    StepperHeaderComponent,
    AddFilesComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MATERIAL,
    ApiModule.forRoot(getConfiguration),
    WebcamModule,

    /* TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }), */

  ],
  exports: [
    ReactiveFormsModule,
    FormsModule,
    WebcamModule,
    MATERIAL,
    PreviewComponent,
    WebCamComponent,
    StepperHeaderComponent
  ]
})
export class ShareModule { }
