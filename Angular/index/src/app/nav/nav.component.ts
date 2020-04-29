import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges, OnChanges } from '@angular/core';
import { Category } from '../category/category';

@Component({
  selector: 'web-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit, OnChanges {
  sCategoryTitle: string;
  @Input() categories: Category[];
  @Output() callSelectC = new EventEmitter<string>();
  @Input() selectedCategory: string;

  constructor(){}

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes && changes.selectedCategory) {
      this.selectedCategory = changes.selectedCategory.currentValue;
      this.setTitle();
    }
  }

  setTitle() {
    const category =
      this.categories.filter((item) => item.value === this.selectedCategory);
    this.sCategoryTitle =
      category && category[0] ? category[0].title : '';
  }

  select(habla: string) {
    this.callSelectC.emit(habla);
  }

}


