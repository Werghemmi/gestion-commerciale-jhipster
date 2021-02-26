import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { WerghemmiGestionCommerclaieTestModule } from '../../../test.module';
import { TaxeDetailComponent } from 'app/entities/taxe/taxe-detail.component';
import { Taxe } from 'app/shared/model/taxe.model';

describe('Component Tests', () => {
  describe('Taxe Management Detail Component', () => {
    let comp: TaxeDetailComponent;
    let fixture: ComponentFixture<TaxeDetailComponent>;
    const route = ({ data: of({ taxe: new Taxe(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [WerghemmiGestionCommerclaieTestModule],
        declarations: [TaxeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TaxeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TaxeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load taxe on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.taxe).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
