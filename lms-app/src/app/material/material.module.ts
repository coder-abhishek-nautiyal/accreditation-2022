import { NgModule } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import {MatExpansionModule} from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatPaginatorModule} from '@angular/material/paginator';


@NgModule({
  exports: [
    MatToolbarModule,
    MatButtonModule,
    MatDividerModule,
    MatToolbarModule,
    MatDividerModule,
    MatIconModule,
    MatInputModule,
    MatCardModule,
    MatGridListModule,
    MatListModule,
    MatTableModule,
    MatExpansionModule,
    MatFormFieldModule,
    MatProgressSpinnerModule,
    MatPaginatorModule
  ]
})
export class MaterialModule { }
