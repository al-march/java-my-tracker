import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TemplateModule } from '@app/core/template';
import { ReactiveFormsModule } from '@angular/forms';
import { Config } from '@app/core/services';
import { AuthInterceptor } from '@app/core/interceptors';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    TemplateModule,
    ReactiveFormsModule
  ],
  providers: [
    Config,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      useFactory: resourceProviderFactory,
      deps: [],
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}


function resourceProviderFactory() {
  return () => Promise.resolve();
}
